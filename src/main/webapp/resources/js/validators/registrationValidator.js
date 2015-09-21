/*
 *   Проверка правильности заполнения формы регистрации
 */

$(function () {

    $("#register-form").validate({

        /*
         *   Установка условий для проверки формы регистрации
         */
        rules: {
            clientName: {
                required: true
            },

            email: {
                required: true,
                email: true
            },

            password: {
                required: true,
                minlength: 6
            },

            secondaryPassword: {
                required: true,
                minlength: 6,
                equalTo: "#password"
            },
        },

        /*
         *   Выводимые сообщения для текущих условий
         */
        messages: {

            clientName: {
                required: "Введите, пожалуйста, имя."
            },

            password: {
                required: "Введите, пожалуйста, пароль.",
                minlength: "Длина пароля не менее 6 символов."
            },

            secondaryPassword: {
                required: "Введите, пожалуйста, пароль.",
                minlength: "Длина пароля не менее 6 символов.",
                equalTo: "Пароли не совпадают. Повторите ввод."
            },

            email: {
                required: "Введите, пожалуйста, E-mail.",
                email: "Введите, пожалуйста, корректный E-mail."
            },

        },

        submitHandler: function (form) {
            form.submit();
        }
    });
});
