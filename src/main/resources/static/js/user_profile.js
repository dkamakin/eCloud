let USER_INFO = {};
let container = document.getElementById('personal_info_container')
container.classList.add('loading');

axios.get('/profile/info')
    .then((response) => {
        USER_INFO = response.data;
        container.innerHTML = '<h4>' + USER_INFO['nickname'] + '</h4>';
        container = document.getElementById('nickname')
        container.innerHTML = USER_INFO['nickname'];
        container = document.getElementById('email')
        container.innerHTML = USER_INFO['username'];
    }).finally(() => {
    container.classList.remove('loading');
});
