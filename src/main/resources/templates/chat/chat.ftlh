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
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    function loadMessages() {
        $.ajax({
            url: '/api/chat/' + ${respondedApplicantId} + '/messages',
            method: 'GET',
            success: function(messages) {
                $('#chatContainer').empty();

                messages.forEach(function(message) {
                    var messageContent = '<div class="message-container"><div>' + message.userFromId.name + ': ' + message.content + '</div></div>';
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
        }, 20000);
    });
</script>

</body>
</html>
