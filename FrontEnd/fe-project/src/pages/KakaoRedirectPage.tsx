import { useNavigate, useLocation } from "react-router-dom";
import { useEffect } from "react";
import { loginAPI } from "../api/loginAPI.tsx";
import loadingImage from "../assets/lodingImages/loading.gif";

export default function KakaoRedirectPage() {
  const location = useLocation();
  const navigate = useNavigate();

  const handleOAuthKakao = async (code: string) => {
    try {
      // 카카오로부터 받아온 code를 서버에 전달하여 카카오로 회원가입 & 로그인한다
      const response = await loginAPI.getKakaoLogin(code);
      const data = response.data; // 응답 데이터
      //토큰 받아서 localstorage에 저장
      localStorage.setItem("accessToken", data.accessToken);
      localStorage.setItem("nickname", data.nickname);
      localStorage.setItem("memberId", data.memberId);
      navigate("/main", { replace: true }); // 성공 시 리다이렉트할 경로
    } catch (error) {
      console.error("로그인 실패", error);
      // navigate("/");  // 실패 시 리다이렉트할 경로
    }
  };

  useEffect(() => {
    const searchParams = new URLSearchParams(location.search);
    const code = searchParams.get("code"); // 카카오는 Redirect 시키면서 code를 쿼리 스트링으로 준다.
    if (code) {
      // alert("CODE = " + code);
      handleOAuthKakao(code);
    }
  }, []);

  return (
    <div className="flex flex-col  my-20">
      <div className="flex justify-center">
        <img src={loadingImage} alt="loding" />
      </div>
      <div className="flex justify-center font-['Pretendard'] font-extrabold text-xl">
        카카오 로그인 중
      </div>
    </div>
  );
}
