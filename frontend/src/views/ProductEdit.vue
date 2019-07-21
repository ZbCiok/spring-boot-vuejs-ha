<template>
<layout name="LayoutDefault">
  <div>
    <br/><br/> 
    <h2 class="show">Object Edit</h2>
    <form @submit.prevent="formSubmit">
      <table class="outer-show">
        <tr>
          <td>
            <table class="show">
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
                    :disabled="false"
                    class="input-field-text"
                    v-model="product.description"
                  />
                </td>
              </tr>
              <tr>
                <td>Country:</td>
                <td>
                  <input
                    type="text"
                    :disabled="false"
                    class="input-field"
                    v-model="product.country"
                  />
                </td>
              </tr>
              <tr>
                <td>Image:</td>
                <td>
                  <input type="text" :disabled="false" class="input-field" v-model="product.image" />
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
      <button>Save</button>
    </form>
  </div>
</layout>
</template>

<script>
import ProductService from "@/services/ProductService.js";
import Consts from "@/consts/Consts.js";
import Layout from "@/layouts/Layout";

export default {
  name: "Product Edit",
  components: {
    Layout
  },

  image: {},
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
    formSubmit(e) {
      //e.preventDefault();
      this.dialogConfirm();
    },

    putCategory(url) {
      ProductService.putEvent(url + this.product.id, this.product)
        .then(response => {
          this.product = response.data[this.index];
        })
        .catch(error => {
          console.log("putCategory(): There was an error:", error.response);
        });
    },

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

    dialogConfirm() {
      this.$swal({
        animation: false,
        allowOutsideClick: false,
        allowEscapeKey: false,
        allowEnterKey: false,
        backdrop: true,

        title: "Save data?",
        text: "",
        type: "question",
        showCancelButton: true,
        confirmButtonColor: "#33a5ff ",
        cancelButtonColor: "#33a5ff ",
        confirmButtonText: "Yes",
        cancelButtonText: "No"
      }).then(result => {
        if (result.value) {
          if (this.$session.get("selected") == "0") {
            this.putCategory("/category/tank/");
          } else if (this.$session.get("selected") == "1") {
            this.putCategory("/category/bomber/");
          } else if (this.$session.get("selected") == "2") {
            this.putCategory("/category/warship/");
          }
          // this.$router.push({ name: "product-list" });
          // window.open("/product-list", "_self");
        }
      });
    }
  }
};
</script>

<style>
table.outer-show td {
  border: solid 0px #ddd;
}

table.show {
  border: solid 0px #ddd;
  margin: 0px;
  padding: 0px;
  border-spacing: 0px;
  border-collapse: collapse;
  line-height: 22px;
  font-size: 16px;
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
}

table.show td {
  border: solid 0px #ddd;
  margin-right: 10;
  padding-right: 14px;
}

table.show tr {
  margin: 0;
  padding: 4px;
}

h2.show {
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
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
