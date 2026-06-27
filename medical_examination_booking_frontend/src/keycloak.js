import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
    url: 'http://localhost:8080',
    realm: 'medicalRealm',
    clientId: 'medical-frontend',
});

keycloak.init({
    onLoad: "login-required",
    checkLoginIframe: false,
}).then(authenticated => {
    console.log("Authenticated:", authenticated);
});

export default keycloak;
