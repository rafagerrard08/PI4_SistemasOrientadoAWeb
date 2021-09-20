import Http from "./http";

export default {

  listar() {
    return Http.get(`/produtos`);
  },

  salvar(produto) {
    return Http.post(`/produtos`, produto);
  },
	
}