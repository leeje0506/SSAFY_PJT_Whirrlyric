import { Outlet } from "react-router-dom";
import Header from "../components/common/Header";
import Footer from "../components/common/Footer";

export default function RootLayout() {
  return (
    <>
      <Header />
      <div className="w-[430px] pt-[128px] mx-auto">
        <Outlet />
      </div>
      <Footer />
    </>
  );
}
