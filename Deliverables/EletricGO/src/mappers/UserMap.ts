import IRoleDTO from "src/dtos/login/IRoleDTO";
import ITruckDTO from "src/dtos/truck/ITruckDTO";
import { IUserDTO } from "src/dtos/user/IUserDTO";
import { IRole } from "src/model/IRole";
import ITruck from "src/model/ITruck";
import { IUser } from "src/model/IUser";

export class UserMap {

    public static toModel(dto: IUserDTO): IUser {
        return{
            userName: dto.userName,
            firstName: dto.firstName,
            lastName: dto.lastName,
            phoneNumber: dto.phoneNumber,
            email: dto.email,
            password: dto.password,
            role: dto.role,
            active: dto.active
        }
    }

    public static toModelList(dtos: IUserDTO[]): IUser[] {
        const list: IUser[] = []

        dtos.forEach(element => {
            list.push(this.toModel(element))
        });

        return list
    }

    public static toDTO(user: IUser): IUserDTO {
        return{
            userName: user.userName,
            firstName: user.firstName,
            lastName: user.lastName,
            phoneNumber: user.phoneNumber,
            email: user.email,
            password: user.password,
            role: user.role,
            active: user.active
        }
    }  
    
    public static toDTOList(trucks: IUser[]): IUserDTO[] {
        const list: IUserDTO[] = []

        trucks.forEach(element => {
            list.push(this.toModel(element))
        });

        return list
    }
}