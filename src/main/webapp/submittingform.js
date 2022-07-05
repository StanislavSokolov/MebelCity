    function submitting(name, email, phone, message) {

        var idObj = {
            "name": name,
            "email": email,
            "phone": phone,
            "message": message,
            "function": "submitting"
        }

        var url = "telegram";

        var responseXML = "";

        $.ajax({
            url: url,
            method: "post",
            data: idObj,
            error: function(message) {
                console.log(message);
            },
            success: function(data) {
                $('#text').html(data);
            }
        });
    };