import Http from "./http";

export default {

    atualizarEstado(id, novoEstado) {
        const body = {
            "estado": novoEstado
        } 
        return Http.put(`/usuarios/${id}/estado`, body);
    },

}