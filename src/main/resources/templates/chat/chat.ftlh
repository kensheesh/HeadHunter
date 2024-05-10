<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta name="_csrf_token" content="${_csrf.token}"/>
    <title>Чат с работодателем</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <style>
        .chat-container {
            background-color: #f8f9fa;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
        }

        .message-container {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/profile">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/vacancies">Vacancies</a>
                </li>

                <#if type = "EMPLOYER">
                    <li class="nav-item">
                        <a class="nav-link" href="/resumes">Resumes</a>
                    </li>
                </#if>

                <#if type = "APPLICANT">
                    <li class="nav-item">
                        <a class="nav-link" href="/users/employers">Employers</a>
                    </li>
                </#if>

                <#if type = "EMPLOYER">
                    <li class="nav-item">
                        <a class="nav-link" href="/users/applicants">Applicants</a>
                    </li>
                </#if>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row mt-5">
        <div class="col-md-4">
            <#if guestUser.accountType == "EMPLOYER">
                <h4 class="mb-3">Информация о работодателе:</h4>
                <div class="col-5 mt-2">
                    <img class="avatar rounded-circle " style="width: 6rem; height: 6rem" src="/profile/avatars/${guestUser.id}" alt="photo">
                </div>
                <p>Название: ${guestUser.name}</p>
            <#else>
                <h4 class="mb-3">Информация о соискателе:</h4>
                <div class="col-5 mt-2">
                    <img class="avatar rounded-circle " style="width: 6rem; height: 6rem" src="/profile/avatars/${guestUser.id}" alt="photo">
                </div>
                <p>Имя: ${guestUser.name} ${guestUser.surname}</p>
            </#if>
            <p>Email: ${guestUser.email}</p>
            <p>Номер телефона: ${guestUser.phoneNumber}</p>
        </div>
        <div class="col-md-8 mx-auto">
            <div class="chat-container" id="chatContainer">
            </div>
            <form id="sendMessageForm">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                </#if>
                <div class="mb-3">
                    <label for="messageContent" class="form-label">Сообщение</label>
                    <textarea class="form-control" id="messageContent" rows="1"></textarea>
                </div>
                <button type="button" id="sendMessageBtn" class="btn btn-primary">Отправить</button>
            </form>
        </div>

    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function () {
        function isResumeLink(content) {
            const resumeLinkRegex = /^\/resumes\/\d+$/;
            return resumeLinkRegex.test(content);
        }

        function getResumeIdFromLink(content) {
            const resumeLinkRegex = /^\/resumes\/(\d+)$/;
            const match = content.match(resumeLinkRegex);
            if (match && match[1]) {
                return match[1];
            } else {
                return null;
            }
        }

        function loadMessages() {
            $.ajax({
                url: '/api/chat/${respondedApplicantId}/messages',
                method: 'GET',
                beforeSend: function(xhr) {
                    // Устанавливаем CSRF токен в заголовок запроса
                    xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
                },
                success: function (messages) {
                    $('#chatContainer').empty();

                    messages.forEach(function (message) {
                        let messageContent = '<div class="message-container"><div>'
                            + message.userFromId.name + ': ';

                        if (isResumeLink(message.content)) {
                            const resumeId = getResumeIdFromLink(message.content);
                            if (resumeId) {
                                messageContent += '<a href="/resumes/' + resumeId + '">' + message.content + '</a>';
                            } else {
                                messageContent += message.content;
                            }
                        } else {
                            messageContent += message.content;
                        }

                        messageContent += '</div></div>';

                        $('#chatContainer').append(messageContent);
                    });
                },
                error: function () {
                    console.error('Ошибка загрузки сообщений');
                }
            });
        }

        loadMessages();

        setInterval(function () {
            loadMessages();
        }, 10000);

        $('#sendMessageBtn').click(function () {
            const messageContent = $('#messageContent').val();

            $.ajax({
                url: '/api/chat/${respondedApplicantId}/send',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    userFromId: ${userFromId},
                    userToId: ${userToId},
                    respondedApplicantsId: ${respondedApplicantId},
                    content: messageContent,
                    timestamp: new Date().toISOString()
                }),
                beforeSend: function(xhr) {
                    // Устанавливаем CSRF токен в заголовок запроса
                    xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
                },
                success: function (response) {
                    loadMessages();
                    $('#messageContent').val('');
                },
                error: function (err) {
                    console.error('Ошибка');
                }
            });
        });
    });
</script>
</body>
</html>
