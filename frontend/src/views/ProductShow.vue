<template>
  <layout name="LayoutDefault">
    <div>
      <br/><br/>
      <h2 class="show">Object Show</h2>
      <br />
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
                  <input type="text" :disabled="true" class="input-field" v-model="product.name" />
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
            <img v-bind:src="product.image" height="300" width="450" border="1" />
          </td>
        </tr>
      </table>
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

  image: "",
  props: ["id"],
  data() {
    return {
      product: []
    };
  },

  created() {
    if (this.$session.get("selected") == "0") {
      ProductService.getProducts("/category/tank")
        .then(response => {
          this.product = response.data[this.id];
          this.image = Consts.tank_images_uri + this.product.image;
          this.product.image = this.image;
        })
        .catch(error => {
          console.log("ProductShow: There was an error:", error.response);
        });
    } else if (this.$session.get("selected") == "1") {
      ProductService.getProducts("/category/bomber")
        .then(response => {
          this.product = response.data[this.id];
          this.image = Consts.bomber_images_uri + this.product.image;
          this.product.image = this.image;
        })
        .catch(error => {
          console.log("ProductShow: There was an error:", error.response);
        });
    } else if (this.$session.get("selected") == "2") {
      ProductService.getProducts("/category/warship")
        .then(response => {
          this.product = response.data[this.id];
          this.image = Consts.warship_images_uri + this.product.image;
          this.product.image = this.image;
        })
        .catch(error => {
          console.log("ProductShow: There was an error:", error.response);
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
