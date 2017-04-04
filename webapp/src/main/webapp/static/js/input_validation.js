$(document).ready(function() {
    $('#form').bootstrapValidator({
        container: '#messages',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
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
                        regexp: /^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/i,
                        message: 'The date has to be YYYY-MM-DD'
                    },
                    callback: {
                        message: 'Discontinued date must be after introduced date',
                        callback: function(value, introduced, $field){

                            var intro = value;
                            var disc = $("#discontinued").val();

                            if(intro==='' || disc==='') {
                                return true;
                            }

                            var discYear = disc.substring(0,4);
                            var discMonth = disc.substring(5,7);
                            var discDay = disc.substring(8,10);

                            var discontinuedDate = new Date(discYear,discMonth-1,discDay);

                            var introYear = intro.substring(0,4);
                            var introMonth = intro.substring(5,7);
                            var introDay = intro.substring(8,10);

                            var introducedDate = new Date(introYear,introMonth-1,introDay);

                            return introducedDate < discontinuedDate;
                        }

                    }
                }
            },
            discontinued: {
                validators: {
                    regexp: {
                        regexp: /^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/i,
                        message: 'The date has to be YYYY-MM-DD'
                    },
                    callback: {
                        message: 'Discontinued date must be after introduced date',
                        callback: function(value, discontinued, $field){

                            var intro = $("#introduced").val();
                            var disc = value;

                            if(intro==='' || disc==='') {
                                return true;
                            }

                            var discYear = disc.substring(0,4);
                            var discMonth = disc.substring(5,7);
                            var discDay = disc.substring(8,10);

                            var discontinuedDate = new Date(discYear,discMonth-1,discDay);

                            var introYear = intro.substring(0,4);
                            var introMonth = intro.substring(5,7);
                            var introDay = intro.substring(8,10);

                            var introducedDate = new Date(introYear,introMonth-1,introDay);

                            return introducedDate < discontinuedDate;
                        }

                    }
                }
            }
        }
    });

});

$('#form').submit(function () {
    $(this)
        .find('input[name]')
        .filter(function () {
            return !this.value;
        })
        .prop('name', '');
});