// var myModal = new bootstrap.Modal(document.getElementById('myModal'), options)
// console.log(myModal);
//  var allRoles = document.getElementById('allRoles');
// console.dir(allRoles);
$(document).ready(function() {
    // let roles = $(user.roles);
    // console.log(roles);
    $('.table .eBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');


        $.get(href,function (user,status){
            console.log(user)
            $('.editModalForm #modalId').val(user.id);
            $('.editModalForm #modalName').val(user.username);
            $('.editModalForm #modalLastName').val(user.lastname);
            $('.editModalForm #modalAge').val(user.age);
            $('.editModalForm #modalEmail').val(user.email);
            $('.editModalForm #modalPassword').val(user.password);
            // $('.editModalForm #modalRoles').val(user.modalRoles);
        });

        $('.editModalForm #editModal').modal('show');
    });
});