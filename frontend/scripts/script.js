'use strict';

document.getElementById('url').focus();

const copy = document.getElementById('copy-box');
copy.addEventListener('click', (e) => {
    const label = document.getElementById('response');
    navigator.clipboard.writeText(label.innerText);
    alert('URL copied!');
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
    const spinner = document.getElementById('spinner');
    spinner.classList.toggle('visually-hidden');
    const input = getInputField();
    const label = document.getElementById('response');
    const div = document.getElementById('label-div');
    if (input.value === '') {
        document.getElementById('error').innerHTML = 'Please enter a valid URL!';
        modal.toggle();
    } else {
        const value = input.value;
        const isValid = checkURL(value);
        if (isValid) {
            const response = await (await fetch(`https://minify-url-1.herokuapp.com/?url=${value}`, { method: 'POST' })).json();
            spinner.classList.toggle('visually-hidden');
            label.innerHTML = `<a href="${response.short_url}">${response.short_url}</a>`;
            div.classList.remove('visually-hidden');
        } else {
            document.getElementById('error').innerHTML = 'Please enter the url<br>Like: https://www.google.com';
            modal.toggle();
        }
    }
});