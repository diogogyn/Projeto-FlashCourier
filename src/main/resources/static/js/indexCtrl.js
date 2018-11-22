app.controller("indexCtrl", function($scope, $http){
    console.log("está funcionando perfeitamente a minha estrutura em angular js com spring mvc!!!");
    console.log("Passou por indexCtrl!");
    // VARIABLES =============================================================
    var TOKEN_KEY = "jwtToken"
    var $notLoggedIn = $("#notLoggedIn");
    var $loggedIn = $("#loggedIn").hide();
    var $loggedInBody = $("#loggedInBody");
    var $response = $("#response");
    var $login = $("#login");
    var $userInfo = $("#userInfo").hide();
    var $formrastreio = $("#formrastreio");
    var $rastreios = $("#rastreios");
    var $rastreioDetail = $("#rastreioDetail");

    // FUNCTIONS =============================================================
    function getJwtToken() {
        return localStorage.getItem(TOKEN_KEY);
    }

    function setJwtToken(token) {
        localStorage.setItem(TOKEN_KEY, token);
    }

    function removeJwtToken() {
        localStorage.removeItem(TOKEN_KEY);
    }

    function doLogin(loginData) {
        console.log("Executou função doLogin")
        $.ajax({
            url: "/auth",
            type: "POST",
            data: JSON.stringify(loginData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                setJwtToken(data.token);
                $login.hide();
                $notLoggedIn.hide();
                showTokenInformation();
                showUserInformation();
                //listrastreios();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    $('#loginErrorModal')
                        .modal("show")
                        .find(".modal-body")
                        .empty()
                        .html("<p>Spring exception:<br>" + jqXHR.responseJSON.exception + "</p>");
                } else {
                    throw new Error("an unexpected error occured: " + errorThrown);
                }
            }
        });
    }

    function buscarRastreio(rastreioData) {
        console.log("tentando enviar os dados para a API");
        console.log(rastreioData);
        $.ajax({
            url: "/buscarastreio/"+rastreioData.id,
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
                $scope.rastreiosLista = data;
                //listrastreios();// para atualizar a lista de rastreios
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    $('#loginErrorModal')
                        .modal("show")
                        .find(".modal-body")
                        .empty()
                        .html("<p>Spring exception:<br>" + jqXHR.responseJSON.exception + "</p>");
                } else {
                    throw new Error("an unexpected error occured[rastreio]: " + errorThrown);
                }
            }
        });
    }

    function listrastreios() {
        $rastreios.show();
        $rastreioDetail.hide();
        $http({
            url: "/listrastreios",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            headers: createAuthorizationTokenHeader()
        }).success(function (data, textStatus, jqXHR) {
            //console.log("listando os rastreamentos");
            $scope.rastreios = data;
        });
    }

    $scope.showrastreioDetail = function(rastreio){
        $rastreioDetail.show();
        //$scope.rastreioDescription = rastreio;
        $rastreioDetail.show();
        console.log("ID: "+rastreio.id);
        $http({
            url: "/buscarastreio/" + rastreio.id,
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            headers: createAuthorizationTokenHeader()
        }).success(function (data, textStatus, jqXHR) {
            //listrastreios();// para atualizar a lista de rastreios
            //console.log(data);
            $scope.rastreioStatus = data;
            console.log(data);
        }).error(function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.status === 401) {
                $('#loginErrorModal')
                    .modal("show")
                    .find(".modal-body")
                    .empty()
                    .html("<p>Spring exception:<br>" + jqXHR.responseJSON.exception + "</p>");
            } else {
                throw new Error("Um erro ocorreu ao tentar excluir: " + errorThrown);
            }
        });
    }

    function doLogout() {
        removeJwtToken();
        $login.show();
        $userInfo
            .hide()
            .find("#userInfoBody").empty();
        $loggedIn.hide();
        $loggedInBody.empty();
        $notLoggedIn.show();
        $formrastreio.hide();
        $rastreios.hide();
    }

    function createAuthorizationTokenHeader() {
        var token = getJwtToken();
        if (token) {
            return {"Authorization": token};
        } else {
            return {};
        }
    }

    function showUserInformation() {
        $userInfo.show();
        $formrastreio.show();
    }

    function showTokenInformation() {
        var jwtToken = getJwtToken();
        var decodedToken = jwt_decode(jwtToken);
        $loggedIn.show();
    }

    function appendKeyValue($table, key, value) {
        var $row = $("<tr>")
            .append($("<td>").text(key))
            .append($("<td>").text(value));
        $table.append($row);
    }

    function showResponse(statusCode, message) {
        $response
            .empty()
            .text("status code: " + statusCode + "\n-------------------------\n" + message);
    }

    // REGISTER EVENT LISTENERS =============================================================
    $("#loginForm").submit(function (event) {
        event.preventDefault();

        var $form = $(this);
        var formData = {
            username: $form.find('input[name="username"]').val(),
            password: $form.find('input[name="password"]').val()
        };

        doLogin(formData);
    });

    $("#logoutButton").click(doLogout);

    $("#rastreioForm").submit(function (event) {
        event.preventDefault();

        var $form = $(this);
        //receber os dados do formulário
        var formData = {
            id: $form.find('input[name="id"]').val()
        };
        var RastreioModel =  new Object();
        RastreioModel.id = formData.id;
        console.log("Recebi os dados no js");
        console.log(RastreioModel);
        buscarRastreio(RastreioModel);
    });

    $loggedIn.click(function () {
        $loggedIn
            .toggleClass("text-hidden")
            .toggleClass("text-shown");
    });




    // INITIAL CALLS =============================================================
    if (getJwtToken()) {
        $login.hide();
        $notLoggedIn.hide();
        showTokenInformation();
        showUserInformation();
        //listrastreios();
    }

});
