$(function () {
    $.validator.setDefaults({ignore: ":hidden:not(select)"});
    $("#newPasswordSave").validate({

        /*
         *   Установка условий для проверки формы регистрации
         */
        rules: {
            oldPassword: "required",
            password: {
                required: true,
                minlength: 5
            },

            secondaryPassword: {
                required: true,
                equalTo: "#password"
            }
        },

        /*
         *   Выводимые сообщения для текущих условий
         */
        messages: {
            oldPassword: {
                required: "Область пароля пуста. Введите Ваш старый пароль."
            },
            password: {
                required: "Область пароля пуста. Введите Ваш новый пароль.",
                minlength: "Пароль, должен быть не меньше 5 символов."
            },

            secondaryPassword: {
                required: "Введите пароль повторно.",
                equalTo: "Пароли не совпадают. Повторите ввод."
            }
        },

        submitHandler: function (form) {
            form.submit();
        }
    });
});

