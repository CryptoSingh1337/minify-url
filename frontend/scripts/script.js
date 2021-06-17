'use strict';

document.getElementById('url').focus();
const modal = new bootstrap.Modal(document.getElementById('staticBackdrop'));
const toast = new bootstrap.Toast(document.getElementById('copy-toast'), { animation: true, autohide: true, delay: 2500 });

document.getElementById('submitButton').addEventListener('click', async function (e) {
    const spinner = document.getElementById('spinner');
    const input = getInputField();
    const label = document.getElementById('response');
    const div = document.getElementById('label-div');
    if (input.value === '') {
        showError('Please enter a valid URL!');
    } else {
        const url = input.value;
        const reqUrl = `https://minify-url-spring-boot.herokuapp.com/api?url=${url}`;
        if (isValid(url)) {
            spinner.classList.toggle('visually-hidden');
            const res = await fetch(reqUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*'
                }
            }).then(async (res) => {
                res = await res.json();
                spinner.classList.toggle('visually-hidden');
                label.innerHTML = `<a href="${res.shortUrl}">${res.shortUrl}</a>`;
                div.classList.remove('visually-hidden');
            });
        } else {
            document.getElementById('error').innerHTML = 'Please enter the url<br>Like: https://www.google.com';
            modal.toggle();
        }
    }
});

function showError(message) {
    document.getElementById('error').innerHTML = message;
    modal.toggle();
}

function isValid(url) {
    const pattern = new RegExp('^htt(p|ps)://www[.].*');
    return pattern.test(url);
}

function getInputField() {
    return document.getElementById('url');
}

const copy = document.getElementById('copy-box');
copy.addEventListener('click', (e) => {
    const label = document.getElementById('response');
    navigator.clipboard.writeText(label.innerText);
    toast.show();
});