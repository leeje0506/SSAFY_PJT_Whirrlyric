import axios, { AxiosInstance } from "axios";

const SERVER = import.meta.env.VITE_LOCAL;


export const defaultAxios: AxiosInstance = axios.create({
  baseURL: SERVER,
});
