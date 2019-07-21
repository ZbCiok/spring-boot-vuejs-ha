<template>
  <layout name="LayoutDefault">
  <div>
    <br/><br/>
    <h2 class="hard-delete">Object Hard Delete</h2>
    <br />    
    <h5 class="hard-delete-cancel">'Hard Delete' cannot be undone.</h5>

    <form @submit.prevent="formSubmit">
      <table class="outer-hard-delete">
        <tr>
          <td>
            <table class="hard-delete">
              <tr>
                <td>Id:</td>
                <td>
                  <input type="text" :disabled="true" class="input-field" v-model="product.id" />
                </td>
              </tr>
              <tr>
                <td>Name:</td>
                <td>
                  <input type="text" :disabled="false" class="input-field" v-model="product.name" />
                </td>
              </tr>
              <tr>
                <td>Description:</td>
                <td>
                  <textarea
                    :disabled="true"
                    class="input-field-text"
                    v-model="product.description"
                  />
                </td>
              </tr>
              <tr>
                <td>Country:</td>
                <td>
                  <input type="text" :disabled="true" class="input-field" v-model="product.country" />
                </td>
              </tr>
            </table>
          </td>
          <td>
            <img v-bind:src="this.image" height="300" width="450" border="1" />
          </td>
        </tr>
      </table>
      <br />
      <button>Delete</button>
    </form>
  </div>
  </layout>
</template>

<script>
import ProductService from "@/services/ProductService.js";
import Consts from "@/consts/Consts.js";
import Layout from "@/layouts/Layout";

export default {
  name: "Product Show",
  components: {
    Layout
  },

  props: ["index"],
  data() {
    return {
      product: {}
    };
  },

  created() {
    if (this.$session.get("selected") == "0") {
      const i = Consts.tank_images_uri;
      this.getCategory("/category/tank", i);
    } else if (this.$session.get("selected") == "1") {
      const i = Consts.bomber_images_uri;
      this.getCategory("/category/bomber", i);
    } else if (this.$session.get("selected") == "2") {
      const i = Consts.warship_images_uri;
      this.getCategory("/category/warship", i);
    }
  },

  methods: {
    getCategory(url, image_uri) {
      ProductService.getProducts(url)
        .then(response => {
          this.product = response.data[this.index];
          this.image = image_uri + this.product.image;
        })
        .catch(error => {
          console.log("getCategory(): There was an error:", error.response);
        });
    },

    formSubmit(e) {
      // e.preventDefault();
      this.dialogConfirm();
    },

    dialogConfirm() {
      this.$swal({
        animation: false,
        allowOutsideClick: false,
        allowEscapeKey: false,
        allowEnterKey: false,
        backdrop: true,

        title: "Delete?",
        text: "",
        type: "question",
        showCancelButton: true,
        confirmButtonColor: "#33a5ff ",
        cancelButtonColor: "#33a5ff ",
        confirmButtonText: "Yes",
        cancelButtonText: "No"
      }).then(result => {
        if (result.value) {
          ProductService.deleteEvent(this.product.id)
            .then(response => {
              this.products = response.data;
            })
            .catch(error => {
              console.log("Delete: There was an error:", error.response);
            });
          // this.$router.push({ name: "product-list" });
          // window.open("/product-list", "_self");
        }
      });
    }
  }
};
</script>

<style>
table.outer-hard-delete td {
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
  border: solid 0px #ddd;
}
table.hard-delete td {
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
  border: solid 0px #ddd;
}
h2.hard-delete {
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
}
h5.hard-delete-cancel {
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
  font-style: italic;
  color: red;
}

.input-field {
  width: 400px;
  font-size: 16px;
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
}

.input-field-text {
  width: 414px;
  height: 200px;
  font-size: 16px;
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
}
</style>


