<template>
  <layout name="LayoutDefault">
    <div class="Home">
      <br />
      <br />
      <h2 class="home">Home</h2>

      <div class="article">
        <div class="col-left">
          <img
            src="http://localhost:8080/images/others/Soldier_of_Polish_Infantry_1939.jpg"
            border="1"
          />
          <br />
          <span style="font-size: 12px">Soldier of Polish Infantry 1939.</span>
        </div>

        <div class="col-right">
          <span class="military-equipment-1">Military Equipment</span>
          <p>
            This is an implementation of an app that lists samples of military equipment, using Spring boot and Vue Js.
            <br />The app is not yet complete, other functionalities will later be implemented.
          </p>
        </div>
      </div>
      <br/>
      <h4
        class="home"
      >The descriptions and photos contained in the application were taken from Wikipedia.</h4>
    </div>
  </layout>
</template>

<script>
import Layout from "@/layouts/Layout";

export default {
  name: "Home",
  components: {
    Layout
  }
};
</script>

<style>
h2.home {
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
}
h4.home {
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
  color: royalblue;
  font-style: italic;
  margin-top: 80px;
}
span.military-equipment-1 {
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
  font-size: 20px;
  font-weight: bold;
  font-style:italic
}

.article {
  margin-top: 50px;
  background-color: #d7d7d7;
  margin-bottom: 280px;
}
.col-left {
  float: left;
  background-color: #ffffff;
  height: 280px;
}
.col-right {
  margin-left: 50px;
  float: left;
  background-color: #ffffff;
  height: 280px;
}
</style>
