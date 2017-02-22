$(document).ready(function() {
    $('#addForm').bootstrapValidator({
        container: '#messages',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            computerName: {
                validators: {
                    notEmpty: {
                        message: 'The name is required and cannot be empty'
                    },
                    stringLength: {
                        max: 250,
                        message: 'The name must be less than 250 characters long'
                    }
                }
            },
            introduced: {
                validators: {
                    regexp: {
                        regexp: /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/i,
                        message: 'The date has to be DD/MM/YYYY'
                    }
                }
            },
            discontinued: {
                validators: {
                    regexp: {
                        regexp: /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/i,
                        message: 'The date has to be DD/MM/YYYY'
                    }
                }
            }
        }
    });
});