/**
 * Проверка правильности ввода полей для добавления нового объявления
 */
$(function () {

    $("#advertForm").validate({

        rules: {
            title: {
                required: true,
                maxlength: 65
            },

            note: {
                maxlength: 700
            },

            category: {
                required: true
            },

            location: {
                required: true
            }
        },

        messages: {
            title: {
                required: "Введите, пожалуйста, название объявления.",
                maxlength: "Длина названия объявления не может превышать 65 символов."
            },

            note: {
                maxlength: "Длина описания не может превышать 700 символов."
            }

        }


    });

});
