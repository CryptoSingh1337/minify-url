'use strict';
const copy = document.getElementById('copy');
copy.addEventListener('click', (e) => {
    const input = document.getElementById('url');
    input.select();
    document.execCommand('copy');
});

function checkURL(url) {
    const pattern = new RegExp('^htt(p|ps)://');
    return pattern.test(url);
}

function getInputField() {
    return document.getElementById('url');
}

const modal = new bootstrap.Modal(document.getElementById('staticBackdrop'));

document.getElementById('submitButton').addEventListener('click', async function (e) {
    const input = getInputField();
    const label = document.getElementById('response');
    const div = document.getElementsByClassName('response');
    if (input.value === '') {
        document.getElementById('error').innerHTML = 'Please enter a valid URL!';
        modal.toggle();
    } else {
        const value = input.value;
        const isValid = checkURL(value);
        if (isValid) {
            const response = await (await fetch(`https://minify-url-1.herokuapp.com/?url=${value}`, { method: 'POST' })).json();
            label.innerText = response.short_url;
            div[0].classList.toggle('visually-hidden');
        } else {
            document.getElementById('error').innerHTML = 'Please enter the url<br>Like: https://www.google.com';
            modal.toggle();
        }
    }
});