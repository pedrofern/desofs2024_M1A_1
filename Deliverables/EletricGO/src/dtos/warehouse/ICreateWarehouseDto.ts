export interface ICreateWarehouseDto {
    identifier: string;
    designation: string;
    streetName: string;
    doorNumber: number;
    city: string;
    country: string;
    zipCode: string;
    latitude: number;
    longitude: number;
    active: boolean;
}
