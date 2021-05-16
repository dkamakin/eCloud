let container = document.getElementById('posts')
container.classList.add('loading');

axios.get('/uploads')
    .then((response) => {
        for (var name in response.data) {
            container.innerHTML += "<div class=\"card\">\n" +
                "  <div class=\"card-body\">\n" +
                "    <h5 class=\"card-title\"> " + response.data[name] + "</h5>\n" +
                "    <p class=\"card-text\">Description</p>\n" +
                "    <a href=\"/uploads/" + response.data[name] + "\" class=\"btn btn-primary\">Download</a>\n" +
                "  </div>\n" +
                "</div>"
        }
    }).finally(() => {
    container.classList.remove('loading');
});
