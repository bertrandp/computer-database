$(document).ready(function() {
    $('#form').bootstrapValidator({
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

                            var introYear = intro.substring(0,4);
                            var introMonth = intro.substring(5,7);
                            var introDay = intro.substring(8,10);

                            if(discYear > introYear) {
                                return true;
                            } else if (discYear == introYear) {
                                if(discMonth > introMonth) {
                                    return true;
                                } else if (discMonth == introMonth) {
                                    if(discDay > introDay) {
                                        return true;
                                    }
                                }
                            }

                            return false;
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

                            var discDay = disc.substring(0,2);
                            var discMonth = disc.substring(3,5);
                            var discYear = disc.substring(6,10);

                            var introDay = intro.substring(0,2);
                            var introMonth = intro.substring(3,5);
                            var introYear = intro.substring(6,10);

                            if(discYear > introYear) {
                                return true;
                            } else if (discYear == introYear) {
                                if(discMonth > introMonth) {
                                    return true;
                                } else if (discMonth == introMonth) {
                                    if(discDay > introDay) {
                                        return true;
                                    }
                                }
                            }

                            return false;
                        }

                    }
                }
            }
        }
    });

});