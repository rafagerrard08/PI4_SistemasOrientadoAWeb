import Http from "./http";

export default {

  listar() {
    return Http.get(`/produtos`);
  },
	
}