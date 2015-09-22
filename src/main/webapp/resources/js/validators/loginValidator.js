/**
 * Проверка правильности ввода формы логина
 */
$(function () {

    $("#loginForm").validate({

        /*
         *   Установка условий для проверки формы регистрации
         */
        rules: {
            email: {
                required: true,
                email: true
            },

            password: {
                required: true,
                minlength: 6
            }
        },

        /*
         *   Выводимые сообщения для текущих условий
         */
        messages: {
            email: {
                required: "Введите, пожалуйста, E-mail.",
                email: "Введите, пожалуйста, коректный E-mail."
            },

            password: {
                required: "Введите, пожалуйста, пароль.",
                minlength: "Длинна пароля не менее 6 символов."
            }
        },

        submitHandler: function (form) {
            form.submit();
        }
    });
});