import IRoleDTO from "src/dtos/login/IRoleDTO";
import { IRole } from "src/model/IRole";

export class RoleMap {

    public static toModel(dto: IRoleDTO): IRole {
        return{
            id: dto.id,
            name: dto.name
        }
    }

    public static toModelList(dtos: IRoleDTO[]): IRole[] {
        const list: IRole[] = []

        dtos.forEach(element => {
            list.push(this.toModel(element))
        });

        return list
    }

    public static toDTO(role: IRole): IRoleDTO {
        return{
            id: role.id,
            name: role.name
        }
    }

    public static toDTOList(trucks: IRole[]): IRoleDTO[] {
        let list: any[];
        list = [];

        trucks.forEach(element => {
            list.push(this.toModel(element))
        });

        return list
    }
}
