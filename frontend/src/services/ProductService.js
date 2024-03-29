import axios from "axios";

const apiClient = axios.create({
  baseURL: 'http://localhost:8080',
  withCredentials: false, 
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json"
  }
});

export default {
  getProducts(url) {
    return apiClient.get("/products" + url);
  },
  putEvent(id, product) {
    return apiClient.put("/products" + id, product);
  },
  postEvent(product) {
    return apiClient.post("/products", product);
  },
  deleteEvent(id, product) {
    return apiClient.delete("/products/" + id);
  }  
};
