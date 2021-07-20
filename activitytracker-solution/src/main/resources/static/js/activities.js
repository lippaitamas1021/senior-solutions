window.onload = function () {
    //alert("Hello, JavaScript!");
    console.log("Hello from Console, JavaScript!")

    let url = 'http://localhost:8080/api/activities';
    fetch(url).then(function (response) {
        return response.json();
    })
        .then(function (jsonData) {
            console.log(jsonData);
            // let ul = document.querySelector("#activities-ul");
            // for (let activity of jsonData) {
            //     let row = "<li>" + activity.name + "</li>"
            //     ul.innerHTML += row;
            // }
        });
}