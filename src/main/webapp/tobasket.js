    function tobasket(id) {

        var idObj = {
            "id": id
        }

        var url = "basket";

        var responseXML = "";

        $.ajax({
            url: url,
            method: "post",
            data: idObj,
            error: function(message) {
                console.log(message);
            },
            success: function(data) {
                $('#Test').html(data);
            }
        });
    };