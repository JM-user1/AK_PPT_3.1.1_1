$(document).ready(function() {
    $('.table .eBtn').on('click', function(event) {

        event.preventDefault();
        var href = $(this).attr('href');

        // $.get(href, function (user,status){
        //     $('#modalId').val(user.id);
        //     $('#modalName').val(user.username);
        //     $('#modalLastName').val(user.id);
        // })

        $.get(href,function (user,status){
            $('.editModalForm #modalId').val(user.id);
            $('.editModalForm #modalName').val(user.username);
            $('.editModalForm #modalLastName').val(user.lastname);
            $('.editModalForm #modalAge').val(user.age);
            $('.editModalForm #modalEmail').val(user.email);
            $('.editModalForm #modalPassword').val(user.password);
            // $('.editModalForm #modalRoles').val(user.modalRoles);
        });

        $('.editModalForm #editModal').modal();
    });
});