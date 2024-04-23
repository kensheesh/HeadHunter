<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
<div class="container">
    <div class="row">
        <div class="col-md-8 mx-auto">
            <h3 class="mt-4 mb-3">Чат с работодателем</h3>
            <div class="chat-container" id="chatContainer">
            </div>
            <form id="sendMessageForm">
                <div class="mb-3">
                    <label for="messageContent" class="form-label">Сообщение</label>
                    <textarea class="form-control" id="messageContent" rows="3"></textarea>
                </div>
                <button type="button" id="sendMessageBtn" class="btn btn-primary">Отправить</button>
            </form>
        </div>

    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function loadMessages() {
        $.ajax({
            url: '/api/chat/${respondedApplicantId}/messages',
            method: 'GET',
            success: function(messages) {
                $('#chatContainer').empty();

                messages.forEach(function(message) {
                    const messageContent = '<div class="message-container"><div>' + message.userFromId.name + ': ' + message.content + '</div></div>';
                    $('#chatContainer').append(messageContent);
                });
            },
            error: function() {
                console.error('Ошибка загрузки сообщений');
            }
        });
    }

    $(document).ready(function() {
        loadMessages();

        setInterval(function() {
            loadMessages();
        }, 10000);

        $('#sendMessageBtn').click(function() {
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
                success: function(response) {
                    loadMessages();
                    $('#messageContent').val('');
                },
                error: function(err) {
                    console.error('Erorr');
                }
            });
        });
    });
</script>

</body>
</html>
