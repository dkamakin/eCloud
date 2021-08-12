let page = 0;
let size = 10;
configPaginationArea();
pagination();

document.getElementById('searchButton').onclick = function () {
    search();
}

document.getElementById('nextButton').onclick = function () {
    pagination();
    page++;
}

document.getElementById('previousButton').onclick = function () {
    page--;
    if (page < 0)
        page = 0;
    pagination();
}

function configPaginationArea() {
    let container = document.getElementById('paginationArea');
    container.classList.add('loading');
    container.innerHTML = "";

    axios.get('/uploads/pages/', {params: {size : size}})
        .then((response) => {
            container.innerHTML += "   <li " +
                "        <a class=\"page-link\" id=\"previousButton\">Previous</a>\n" +
                "      </li>";

            for (let i = 1; i < response.data; i++) {
                container.innerHTML += "<li class=\"page-item\"><a class=\"page-link\" href=" +
                    "/?page="+ i + "?size=" + size + ">" + i +"</li>";
            }

            container.innerHTML += " <li class=\"page-item\">\n" +
                "        <a class=\"page-link\" id=\"nextButton\">Next</a>\n" +
                "      </li>";
        }).finally(() => {
        container.classList.remove('loading');
    });
}

function pagination() {
    let container = document.getElementById('posts');
    container.classList.add('loading');
    container.innerHTML = "";

    axios.get('/uploads/', {params: {page: page, size: size}})
        .then((response) => {
            addPosts(container, response);
        }).finally(() => {
        container.classList.remove('loading');
    });

}

function search() {
    let container = document.getElementById('posts');
    container.classList.add('loading');
    container.innerHTML = "";
    const searchString = document.getElementById('searchField').value;

    axios.get('/uploads/search', {params: {search: searchString}})
        .then((response) => {
            addPosts(container, response);
        }).finally(() => {
        container.classList.remove('loading');
    });
}

function addPosts(container, response) {
    for (let post of response.data) {
        container.innerHTML += "<div class=\"card\">\n" +
            "  <div class='card-header'>" +
            post['university'] +
            "  </div> " +
            "  <div class=\"card-body\">\n" +
            "    <h5 class=\"card-title\"> " + post['name'] + "</h5>\n" +
            "    <p class=\"card-text\">" + post['description'] + "</p>\n" +
            "    <a href=\"/uploads/" + post['userid'] + '/' + post['filename'] + "\" class=\"btn btn-primary\">Download</a>\ " +
            "  <div class='card-footer text-muted'>" +
            post['date'] +
            "  </div>" +
            "  </div>\n" +
            "</div>"
    }
}
