let container = document.getElementById('posts')
container.classList.add('loading');

axios.get('/uploads')
    .then((response) => {
        for (let post of response.data) {
            container.innerHTML += "<div class=\"card\">\n" +
                "  <div class=\"card-body\">\n" +
                "    <h5 class=\"card-title\"> " + post['name'] + "</h5>\n" +
                "    <p class=\"card-text\">" + post['description'] + "</p>\n" +
                "    <a href=\"/uploads/" + post['fileName'] + "\" class=\"btn btn-primary\">Download</a>\n" +
                "  </div>\n" +
                "</div>"
        }
    }).finally(() => {
    container.classList.remove('loading');
});
