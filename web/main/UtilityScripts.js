var UtilityScripts = (function () {




    return {

        updateAttendee: function () {
            var that = this;
            $("#output").append("got to updateAttendee");
            //get value of the attendee id
            var id = $("#attendeemenu").val().trim();

            //get new value for the firstname,lastname,displayname
            var firstname = $("#firstname").val().trim();
            var lastname = $("#lastname").val().trim();
            var displayname = $("#displayname").val().trim();
            data = { "firstname": firstname, "lastname": lastname, "attendeeid": id, "displayname": displayname };

            //ajax put request to attendess serverlet
            $.ajax({
                url: 'http://localhost:8180/Lab6/main/Attendees',
                method: 'PUT',
                data: data,
                dataType: 'json',
                success: function (response) {
                    //that.success(response);
                    if (response.success === true)
                        //directly output response to output div
                        $("#output").html("Update saved successfully!");
                }
            });



        },
        getSessionInfo: function () {
            console.log("get Session Info");
            var sessionid = $("#sessionmenu").val().trim();
            var url = 'http://localhost:8180/Lab6/main/TrainingSessionServerlet?id=' + sessionid;
            $.ajax({
                url: url,
                method: 'GET',
                success: function (response) {
                    //that.success(response);
                    if (response[0].success === true) {
                        //directly output response to output div
                        //console.log(response);

                        //add table of the json information
                        var htmlstring = "<tr><th>Attendee Id</th><th>Session Id</th><th>Firstname</th><th>Lastname</th><th>Display Name</th></tr>";
                        for (let i = 1; i < response.length; i++) {

                            htmlstring += "<tr>";

                            for (const key in response[i]) {
                                //console.log(key, response[i][key]);
                                htmlstring += "<td>";
                                htmlstring += response[i][key];
                                htmlstring += "</td>";
                            }

                            htmlstring += "</tr>";
                        }
                        $("#outputtable").html(htmlstring);
                    }
                }
            });

        },

        updateRegistration: function () {
            console.log("update Registration");

            var attendeeid_old = $("#attendeeid").val().trim();
            var attendeeid_update = $("#attendeeid_update").val().trim();
            var sessionid_old = $("#sessionid").val().trim();
            var sessionid_update = $("#sessionid_update").val().trim();

            data = { "attendeeid_old": attendeeid_old, "sessionid_old": sessionid_old, "attendeeid_updated": attendeeid_update, "sessionid_updated": sessionid_update };

            $.ajax({
                url: 'http://localhost:8180/Lab6/main/registrations',
                method: 'PUT',
                data: data,
                dataType: 'json',
                success: function (response) {
                    //that.success(response);
                    console.log(JSON.stringify(response));
                }
            });
        },

        deleteRegistration: function () {
            console.log("delete registration");
            var attendeeid = $("#attendeeid_delete").val().trim();
            var sessionid = $("#sessionid_delete").val().trim();
            data = { "attendeeid": attendeeid, "sessionid": sessionid };

            $.ajax({
                url: 'http://localhost:8180/Lab6/main/registrations',
                method: 'DELETE',
                data: data,
                dataType: 'json',
                success: function (response) {
                    //that.success(response);
                    console.log(JSON.stringify(response));
                }
            });


        },

    };
})();