window.onload = function () {
    // alert("Hello JavaScript!");
    console.log("Hello JavaScript Console!");

    getEmployees();

    let button = document.querySelector("#create-button");
    let input = document.querySelector("#name-input");
    let yearInput = document.querySelector("#year-of-birth-input");

    button.onclick = function (e) {
      console.log("Button has pressed");

      let name = input.value;
      console.log("Name: " + name);

      let year = yearInput.value;

      let data = {"name": name, "year": year};
      console.log(data);

        let url = 'http://localhost:8080/api/employees';
        fetch(url, {
            method: "POST",
            body: JSON.stringify(data),
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then(function(response) {
                console.log("Success");
                getEmployees();
                input.value = "";
                let messageParagraph = document.querySelector("#message-p");
                messageParagraph.innerHTML = "Success save";
            })

    };

}

function getEmployees() {
    let url = 'http://localhost:8080/api/employees';
    fetch(url)
        .then(function(response) {
            return response.json();
        })
        .then(function(jsonData) {
            console.log(jsonData);
            let ul = document.querySelector("#employees-ul");
            ul.innerHTML = "";

            for (let employee of jsonData) {
                let row = "<li>" + employee.id + " - " + employee.name + "</li>"
                ul.innerHTML += row;
            }
        });
}