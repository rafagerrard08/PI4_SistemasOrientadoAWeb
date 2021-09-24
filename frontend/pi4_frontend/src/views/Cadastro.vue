<template>
  <div class="container" v-if="id == null">
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button
            type="button"
            class="navbar-toggle"
            data-toggle="collapse"
            data-target="#mynavbar"
          >
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a href="index.html" class="navbar-brand">eLoja</a>
        </div>
        <div class="collapse navbar-collapse" id="mynavbar">
          <ul class="nav navbar-nav navbar-right">
            <li>
              <a href=""
                ><span class="glyphicon glyphicon-shopping-cart">
                  Carrinho</span
                ></a
              >
            </li>
            <li>
              <a href=""
                ><span class="glyphicon glyphicon-user"> Configuracoes</span></a
              >
            </li>
            <li>
              <a href=""
                ><span class="glyphicon glyphicon-log-out"> Sair</span></a
              >
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="content">
      <div class="card">
        <div class="card-body">
          <h4 class="card-title">Inclusão Produtos</h4>

          <div class="row">
            <div class="col form-group">
              <label>Nome do Produto</label>
              <input
                required
                v-model="nomeProduto"
                type="text"
                class="form-control form-control-sm"
              />
            </div>
          </div>
          <div class="row">
            <div class="col form-group">
              <label>Marca</label>
              <input
                required
                v-model="marca"
                type="text"
                class="form-control form-control-sm"
              />
            </div>
          </div>
          <div class="row">
            <div class="col form-group">
              <label>Descrição detalhada</label>
              <textarea
                v-model="detalhes"
                type="text"
                class="form-control form-control-sm"
              />
            </div>
          </div>

          <div class="row">
            <div class="col form-group">
              <label>Preço produto</label>
              <input
                required
                v-model="preco"
                type="number"
                class="form-control form-control-sm"
              />
            </div>
          </div>

          <div class="row">
            <div class="col form-group">
              <label>Quantidade estoque</label>
              <input
                required
                v-model="quantidade"
                type="number"
                class="form-control form-control-sm"
              />
            </div>
          </div>
          <div class="row">
            <div class="col form-group">
              <label>Categoria</label><br />
              <select
                class="form-select form-select-sm"
                aria-label=".form-select-sm example"
                v-model="categoria"
              >
                <option selected>Selecionle uma categoria</option>
                <option
                  v-for="categoria of categorias"
                  :key="categoria.id"
                  :value="categoria.id"
                  >{{ categoria.nome }}</option
                >
              </select>
            </div>
          </div>

          <div class="row">
            <div class="col form-group uploadform">
              <label>Insira as Imagem</label>
              <input required type="file" @change="onFileSelect" />
              <button
                class="btn btn-primary btn-sm"
                @click="startUpload"
                name="Upload"
              />
            </div>
          </div>

          <button
            type="submit"
            class="btn btn-primary btn-sm"
            @click.prevent="salvar()"
          >
            <i class="fa fa-search fa-fw"></i>Incluir
          </button>
          <button class="btn btn-secundary btn-sm" onclick="window.location.href = 'index.html'">
            <i class="fa fa-search fa-fw"></i>Cancelar
          </button>
        </div>
      </div>
    </div>
  </div>
  <div class="container" v-else>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button
            type="button"
            class="navbar-toggle"
            data-toggle="collapse"
            data-target="#mynavbar"
          >
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a href="index.html" class="navbar-brand">eLoja</a>
        </div>
        <div class="collapse navbar-collapse" id="mynavbar">
          <ul class="nav navbar-nav navbar-right">
            <li>
              <a href=""
                ><span class="glyphicon glyphicon-shopping-cart">
                  Carrinho</span
                ></a
              >
            </li>
            <li>
              <a href=""
                ><span class="glyphicon glyphicon-user"> Configuracoes</span></a
              >
            </li>
            <li>
              <a href=""
                ><span class="glyphicon glyphicon-log-out"> Sair</span></a
              >
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="content">
      <div class="card">
        <div class="card-body">
          <h4 class="card-title">Inclusão Produtos</h4>

          <div class="row">
            <div class="col form-group">
              <label>Nome do Produto</label>
              <input
                required
                v-model="produto.nome"
                type="text"
                class="form-control form-control-sm"
              />
            </div>
          </div>
          <div class="row">
            <div class="col form-group">
              <label>Marca</label>
              <input
                required
                v-model="produto.marca"
                type="text"
                class="form-control form-control-sm"
              />
            </div>
          </div>

          <div class="row">
            <div class="col form-group">
              <label>Descrição detalhada</label>
              <textarea
                v-model="produto.descricao"
                type="text"
                class="form-control form-control-sm"
              />
            </div>
          </div>

          <div class="row">
            <div class="col form-group">
              <label>Preço produto</label>
              <input
                required
                v-model="produto.preco"
                type="number"
                class="form-control form-control-sm"
              />
            </div>
          </div>

          <div class="row">
            <div class="col form-group">
              <label>Quantidade estoque</label>
              <input
                required
                v-model="produto.quantidade"
                type="number"
                class="form-control form-control-sm"
              />
            </div>
          </div>
          <div class="row">
            <div class="col form-group">
              <label>Categoria</label><br />
              <select
                class="form-select form-select-sm"
                aria-label=".form-select-sm example"
                :value="produto.categoria"
              >
                <option v-if="categoria == produto.categoria" selected>{{
                  categoria.nome
                }}</option>
                <option
                  v-for="categoria of categorias"
                  :key="categoria.id"
                  :value="categoria.id"
                  >{{ categoria.nome }}</option
                >
              </select>
            </div>
          </div>
          <div class="row">
            <div class="col form-group">
              <label>Estado</label><br />
              <select
                class="form-select form-select-sm"
                aria-label=".form-select-sm example"
                :value="produto.estado"
              >
                <option v-if="produto.estado == 'ATIVO'" selected value="ATIVO"
                  >Ativo</option
                >
                <option v-else value="ATIVO">Ativo</option>

                <option
                  v-if="produto.estado == 'INATIVO'"
                  selected
                  value="INATIVO"
                  >Inativo</option
                >
                <option v-else value="INATIVO">Inativo</option>
              </select>
            </div>
          </div>
          <div class="row">
            <div class="col form-group uploadform">
              <label>Insira as Imagem</label>
              <input required type="file" @change="onFileSelect" />
              <button
                type="submit"
                class="btn btn-primary btn-sm"
                @click="startUpload"
                name="Upload"
              />
            </div>
          </div>

          <button class="btn btn-primary btn-sm" @click.prevent="Alterar()">
            <i class="fa fa-search fa-fw"></i>Alterar
          </button>
           <button class="btn btn-secundary btn-sm" onclick="window.location.href = 'index.html'">
            <i class="fa fa-search fa-fw"></i>Cancelar
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProdutoService from "@/services/produtos.js";
import axios from "axios";
import alertUtils from "@/utils/alertUtils";

export default {
  name: "Cadastro",
  components: {},

  data() {
    return {
      nomeProduto: null,
      detalhes: null,
      preco: null,
      quantidade: null,
      marca: null,
      categoria: null,
      produto: [],
      categorias: [],
      formData: null,

      selectedFile: null,
      id: this.$route.params.Id,
    };
  },

  mounted() {
    /*     alert(this.id)
     */ if (this.id != null) {
      this.buscarDadosProduto();
    } else {
      this.ListaCategorias();
    }
  },

  methods: {
    salvar() {
      this.produto.nome = this.nomeProduto;
      this.produto.marca = this.marca;
      this.produto.descricao = this.detalhes;
      this.produto.preco = this.preco;
      this.produto.quantidade = this.quantidade;
      this.produto.categoria = this.categoria;
      console.log(this.produto);
      //debugger;
      axios.post("http://localhost:8080/produtos", {
        nome: this.produto.nome,
        marca: this.produto.marca,
        descricao: this.produto.descricao,
        preco: this.produto.preco,
        quantidade: this.produto.quantidade,
        categoriaId: this.produto.categoria,
      });
    },
    ListaCategorias() {
      axios.get("http://localhost:8080/categorias").then((res) => {
        this.categorias = res.data;
      });
    },
    handleErrors(erro) {
      alertUtils.alertFinalMid(erro, "Ok", "error");
      console.log("erro: ", erro);
    },
    buscarDadosProduto() {
      // alert(this.id)
      axios.get("http://localhost:8080/produtos/" + this.id).then((res) => {
        /*         alert(res.data);
         */ this.produto = res.data;
        this.ListaCategorias();
      });
    },
    onFileSelect(event) {
      this.selectedFile = event.target.files[0];
    },

    startUpload() {
      const fd = new FormData();
      fd.append("files", this.selectedFile, this.selectedFile.name);
      axios.post("http://localhost:8080/produtos/uploadImages/1", fd).then((res) => {
        console.log(res);
      });
    },
    Alterar() {
      /*       alert(JSON.stringify(this.produto))
       */ axios
        .put("http://localhost:8080/produtos/" + this.id, {
          nome: this.produto.nome,
          marca: this.produto.marca,
          quantidade: this.produto.quantidade,
          preco: this.produto.preco,
          estado: this.produto.estado,
          descricao: this.produto.descricao,
          categoriaId: this.produto.categoria.id,
          categoria: {
            id: this.produto.categoria.id,
            nome: this.produto.categoria.nome,
          },
        })
        .then(() => {
          alert("Produto alterado com sucesso!!");
        })
        .catch(() => {
          alert("Não foi possível alterar este produto, tente novamente!!");
        });
    },
  },
};
</script>

<style scoped>


.content{
    padding: 75px;
}

</style>
