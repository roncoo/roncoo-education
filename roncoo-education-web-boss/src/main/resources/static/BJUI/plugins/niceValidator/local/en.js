/* nice validate - Global configuration */
$.validator.config({
    //stopOnError: false,
    //theme: 'yellow_right',
    defaultMsg: "This field is not valid.",
    loadingMsg: "Validating...",
    
    // Custom rules
    rules: {
        digits: [/^\d+$/, "Please enter only digits."]
        
    }
})

/* nice validate - Default error messages */
$.validator.config({
    messages: {
        required: "This field is required.",
        remote: "Please try another name.",
        integer: {
            '*': "Please enter an integer.",
            '+': "Please enter a positive integer.",
            '+0': "Please enter a positive integer or 0.",
            '-': "Please enter a negative integer.",
            '-0': "Please enter a negative integer or 0."
        },
        match: {
            eq: "{0} must be equal to {1}.",
            neq: "{0} must be not equal to {1}.",
            lt: "{0} must be less than {1}.",
            gt: "{0} must be greater than {1}.",
            lte: "{0} must be less than or equal to {1}.",
            gte: "{0} must be greater than or equal to {1}."
        },
        range: {
            rg: "Please enter a number between {1} and {2}.",
            gte: "Please enter a number greater than or equal to {1}.",
            lte: "Please enter a number less than or equal to {1}."
        },
        checked: {
            eq: "Please check {1} items.",
            rg: "Please check between {1} and {2} items.",
            gte: "Please check at least {1} items.",
            lte: "Please check no more than {1} items."
        },
        length: {
            eq: "Please enter {1} characters.",
            rg: "Please enter a value between {1} and {2} characters long.",
            gte: "Please enter at least {1} characters.",
            lte: "Please enter no more than {1} characters.",
            eq_2: "",
            rg_2: "",
            gte_2: "",
            lte_2: ""
        }
    }
})