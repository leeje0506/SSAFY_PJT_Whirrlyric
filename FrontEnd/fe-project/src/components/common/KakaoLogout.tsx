import { useNavigate } from "react-router-dom";
import {loginAPI} from "../../api/loginAPI.tsx";

export default function KakaoLogout() {

    const navigate = useNavigate();

    const kakaoLogout = async () => {
        const response = await loginAPI.kakaoLogout();
        console.log(response);
        navigate("/");
    }


    return (

        <div>
            <button onClick={kakaoLogout} className="text-black font-['Pretendard'] font-extrabold text-xl bg-yellow-400">카카오 로그아웃</button>
        </div>



    );
}
