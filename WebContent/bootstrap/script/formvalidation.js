    //Value of 'btn' set via onclick on submit/clear buttons at end of form
    var btn = "";

    function validate(myform) {

            //Validate Form only on 'submit' button (not for 'clear' button)
            var num = 0;
            var message = "";
            if(myform.firstName.value == "") {
                message += "- first name must be completed \n";
                num = 1;
            } 
            
            if(myform.lastName.value == "") {
                message += "- last name must be completed \n";
                num = 1;
            } 
            
            if(myform.areaCode.value == "") {
                message += "- area code must be completed \n";
                num = 1;
            } 
            
            if(myform.phoneNumber.value == "") {
                message += "- phone number must be completed \n";
                num = 1;
            } 
            
            if(myform.email.value == "") {
                message += "- email must be completed \n";
                num = 1;
            } 
            
            if(myform.street1.value == "") {
                message += "- street must be completed \n";
                num = 1;
            }
            if(myform.city.value == "") {
                message += "- city must be completed \n";
                num = 1;
            }
            if(myform.state.value == "") {
                message += "- state or province must be completed \n";
                num = 1;
            }   
            if(myform.postal.value == "") {
                message += "- postal code must be completed \n";
                num = 1;
            }
            if(myform.country.value == "") {
                message += "- country must be completed \n";
                num = 1;
            }
            if(myform.typeOfRoom.value == "") {
                message += "- type of room must be completed \n";
                num = 1;
            }
            
            
            if(myform.price.value == "") {
                message += "- price must be completed \n";
                num = 1;
            }
            
            if(myform.noOfRooms.value == "") {
                message += "- number of rooms must be completed \n";
                num = 1;
            }
            
            if(myform.noOfGuests.value == "") {
                message += "- number of guests must be completed \n";
                num = 1;
            }           
            
            

            if (num == 1) { 
                alert ("Please complete or correct the following required fields: \n\n"+message);
                return false;
            } else {
                return true;
            } //end if
 
    }