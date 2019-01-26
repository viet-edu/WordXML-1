if (typeof jQuery === "undefined") {
    throw new Error("jQuery plugins need to be before this file");
}

$(function() {
    $('#register-form').submit(function(e) {
        e.preventDefault();
        var valid = true;
        var $form = $(this);
        
        var username = $form.find("input[name=username]").val();
        if (isEmpty(username)) {
            $('.message').html('Please enter username!');
            valid = false;
        } else {
            var password = $form.find("input[name=password]").val();
            if (isEmpty(password)) {
                $('.message').html('Please enter password!');
                valid = false;
            } else {
                var password_confirm = $form.find("input[name=password_confirm]").val();
                if (isEmpty(password_confirm)) {
                    $('.message').html('Please enter password confirm!');
                    valid = false;
                } else {
                    if (password.trim() !== password_confirm.trim()) {
                        $('.message').html('Please enter password not match!');
                        valid = false;
                    }
                }
            }
        }
        
        if (valid) {
            $('.message').htm('');
            $form.submit();
        }
    });
});

function isEmpty(value) {
    return (value == null || value.trim().length === 0);
}
