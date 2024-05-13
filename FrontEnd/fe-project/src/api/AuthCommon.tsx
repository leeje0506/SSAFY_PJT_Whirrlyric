import axios, { AxiosInstance } from "axios";

const SERVER = import.meta.env.VITE_SERVER;

export const defaultAxios: AxiosInstance = axios.create({
  // baseURL: "http://localhost:3000/",
  baseURL: SERVER,
});

export const authAxios: AxiosInstance = axios.create({
  baseURL: SERVER,
  //  baseURL: "http://localhost:3000/",
  headers: {
    Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
  },
});
