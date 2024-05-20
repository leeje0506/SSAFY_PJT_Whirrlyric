import { useNavigate } from "react-router-dom";
import {loginAPI} from "../../api/loginAPI.tsx";

export default function KakaoLogout() {

    const navigate = useNavigate();

    const kakaoLogout = async () => {
        await loginAPI.kakaoLogout();
        localStorage.removeItem("nickname");
        localStorage.removeItem("accessToken");
        localStorage.removeItem("memberId");
        navigate("/");
    }


    return (

        <div>
            <button onClick={kakaoLogout} className="text-gray-500 font-['Pretendard'] font-extrabold text-sm ">로그아웃</button>
        </div>



    );
}
