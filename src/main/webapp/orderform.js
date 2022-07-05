function order(name, surname, address, phone, email, message) {

        var idObj = {
            "name": name,
            "surname": surname,
            "address": address,
            "phone": phone,
            "email": email,
            "message": message,
            "function": "order"
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
            }
        });
    };
