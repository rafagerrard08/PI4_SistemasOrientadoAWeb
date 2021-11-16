<template>
  <div class="container" v-if="id == null">
    <NavbarComponent />
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
                <option selected>Selecione uma categoria</option>
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
              <label>Insira as Imagens</label>
              <input
                type="file"
                accept="image/*"
                multiple="multiple"
                @change="previewMultiImage"
                class="form-control-file"
                id="my-file"
              />

              <div v-if="preview_list.length" class="border p-2 mt-3">
                <template>
                  <div v-for="(item, index) in preview_list" :key="index">
                    <img :src="item" class="img-fluid" style="width: 150px; height: 150px"/>
                    <p class="mb-0">file name: {{ image_list[index].name }}</p>
                    <p>tamanho: {{ image_list[index].size / 1024 }}KB</p>
                    <button class="btn btn-primary" @click="reset(index)">remover imagem</button>
                  </div>
                </template>
              </div>
            </div>
          </div>

          <button
            type="submit"
            class="btn btn-primary btn-sm"
            @click.prevent="salvar()"
          >
            <i class="fa fa-search fa-fw"></i>Incluir
          </button>
          <button
            class="btn btn-secundary btn-sm"
            onclick="window.location.href = 'index.html'"
          >
            <i class="fa fa-search fa-fw"></i>Cancelar
          </button>
        </div>
      </div>
    </div>
  </div>

  <div class="container" v-else>
    <NavbarComponent />
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
                <option v-if="categoriaSelecionado == produto.categoria.nome" selected>{{
                  categoriaSelecionado
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
              <label>Insira as Imagens</label>
              <input
                type="file"
                accept="image/*"
                multiple="multiple"
                @change="previewMultiImage"
                class="form-control-file"
                id="my-file"
              />

              <div v-if="preview_list.length" class="border p-2 mt-3">
                <template>
                  <div v-for="(item, index) in preview_list" :key="index">
                    <img :src="item" class="img-fluid" style="width: 150px; height: 150px"/>
                    <p class="mb-0">file name: {{ image_list[index].name }}</p>
                    <p>tamanho: {{ image_list[index].size / 1024 }}KB</p>
                    <button class="btn btn-primary" @click="reset(index)">remover imagem</button>
                  </div>
                </template>
              </div>
            </div>
          </div>

          <button class="btn btn-primary btn-sm" @click.prevent="Alterar()">
            <i class="fa fa-search fa-fw"></i>Alterar
          </button>
          <button
            class="btn btn-secundary btn-sm"
            onclick="window.location.href = 'index.html'"
          >
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
import NavbarComponent from "../components/NavbarComponent.vue";

export default {
  name: "Cadastro",
  components: { NavbarComponent },

  data() {
    return {
      nomeProduto: null,
      detalhes: null,
      preco: null,
      quantidade: null,
      marca: null,
      categoria: null,
      categoriaSelecionado: null,
      produto: [],
      categorias: [],
      formData: null,
      preview: null,
      image: null,
      preview_list: [],
      image_list: [],
      selectedFile: null,
      id: this.$route.params.Id,
      idAtual: null,
      imagemPrincipalProduto: null,
      imagensProduto: [],
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
    previewMultiImage: function(event) {
      var input = event.target;
      var count = input.files.length;
      var index = 0;
      if (input.files) {
        while (count--) {
          var reader = new FileReader();
          reader.onload = (e) => {
            this.preview_list.push(e.target.result);
          };
          this.image_list.push(input.files[index]);
          reader.readAsDataURL(input.files[index]);
          index++;
        }
      }
    },
    salvar() {
      this.produto.nome = this.nomeProduto;
      this.produto.marca = this.marca;
      this.produto.descricao = this.detalhes;
      this.produto.preco = this.preco;
      this.produto.quantidade = this.quantidade;
      this.produto.categoria = this.categoria;
      console.log(this.produto);
      //debugger;
      axios
        .post("http://localhost:8080/produtos", {
          nome: this.produto.nome,
          marca: this.produto.marca,
          descricao: this.produto.descricao,
          preco: this.produto.preco,
          quantidade: this.produto.quantidade,
          categoriaId: this.produto.categoria,
        })
        .then((res) => {
          this.produto = res.data;

          const config = { headers: { "Content-Type": "multipart/form-data" } };

          for (var i = 0; i < this.image_list.length; i++) {
            const fd = new FormData();
            fd.append("file", this.image_list[i]);
            axios
              .post(
                "http://localhost:8080/produtos/uploadImages/" + this.produto.id,
                fd
              )
              .then((response) => console.log(response))
              .catch((errors) => {
                alert(errors);
              });
          }
        })
        .catch((errors) => {
          alert(errors);
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
         this.categoriaSelecionado = this.produto.categoria.nome
                  alert(this.categoriaSelecionado);

        this.ListaCategorias();
        this.loadImages();
      });
    },
    onFileSelect(event) {
      this.selectedFile = event.target.files[0];
    },

    startUpload() {
      const fd = new FormData();
      fd.append("file", this.selectedFile, this.selectedFile.name);
      axios
        .post("http://localhost:8080/produtos/uploadImages/1", fd)
        .then((res) => {
          console.log(res);
        });
    },
    Alterar() {
      axios
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
          const config = { headers: { "Content-Type": "multipart/form-data" } };
          axios.delete("http://localhost:8080/produtos/resetImages/" + this.id);


          for (var i = 0; i < this.image_list.length; i++) {
                      alert(this.image_list[0]);
            const fd = new FormData();
            fd.append("file", this.image_list[i]);
            axios
              .post(
                "http://localhost:8080/produtos/uploadImages/" + this.id,
                fd
              )
              .then((response) => console.log(response))
              .catch((errors) => {
                alert(errors);
              });
          }
        })
        .catch(() => {
          alert("Não foi possível alterar este produto, tente novamente!!");
        });
    },
    reset(index) {
      this.image_list.splice(index, 1);
      this.preview_list.splice(index, 1);
    },

    loadImages () {
      this.imagemPrincipalProduto =
          "http://localhost:8080/" + this.produto.imagemPrincipal;

        this.image_list.push(this.imagemPrincipalProduto);
        this.preview_list.push(this.imagemPrincipalProduto);
        for (var i in this.produto.imagens) {
          this.image_list.push(
            "http://localhost:8080" + this.produto.imagens[i]
          );
          this.preview_list.push(
            "http://localhost:8080" + this.produto.imagens[i]
          );
        }
    }
  },
};
</script>

<style scoped>
.content {
  padding: 75px;
}
</style>
