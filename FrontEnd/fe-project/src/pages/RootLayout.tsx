import { Outlet } from "react-router-dom";
import Header from "../components/common/Header";
import Footer from "../components/common/Footer";

export default function RootLayout() {
  return (
    <>
      <Header />
      <div className="pt-[128px]">
        <Outlet />
      </div>
      <Footer />
    </>
  );
}
