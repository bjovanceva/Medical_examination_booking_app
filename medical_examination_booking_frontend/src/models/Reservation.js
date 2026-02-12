import doctorRepository from "../repository/doctorRepository.js";
import keycloak from "../keycloak.js";


export default class Reservation {
    constructor({ id, doctorId, patientId, whenDate, whenTime }) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.whenDate = whenDate;
        this.whenTime = whenTime;
        this.doctorName = null;
        this.doctorSurname = null;
        this.price = null;
    }

    async loadDoctor() {
        const response = await doctorRepository.findById(
            this.doctorId,
            keycloak.token
        );
         this.doctorName = response.data?.name
         this.doctorSurname = response.data?.surname
         this.price = response.data?.examination_price
        return this;
    }

    toString() {
        return `Doctor: ${this.doctorName} ${this.doctorSurname} - Time: ${this.whenDate} ${this.whenTime}`;
    }

    getPrice(){
        return this.price;
    }
}