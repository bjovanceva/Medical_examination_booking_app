import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
    url: 'http://localhost:8080',
    realm: 'medicalRealm',
    clientId: 'medical-frontend',
});

export default keycloak;
