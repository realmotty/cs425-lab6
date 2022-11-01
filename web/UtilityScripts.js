var UtilityScripts = (function () {

    var createSessionMenu = function (sessions) {
        $("#sessionlist").append("<select title=\"sessionmenu\" id=\"sessionmenu\">" + "</select name>");

        sessions.forEach(element => {
            $("#sessionmenu").append("<option value=\"" + element['id'] + "\">" + element['id'] + " (" + element['description'] + ")");
        });
    };


    return {
        getSessionlist: function () {
            //get from api
            $.ajax({
                url: 'http://localhost:8180/Lab6/TrainingSessionServerlet',
                method: 'GET',
                dataType: 'json',
                success: function (response) {

                    createSessionMenu(response);

                }
            })

        }
    };
})();