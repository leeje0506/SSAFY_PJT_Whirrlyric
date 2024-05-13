
import logo from "../assets/altImages/logo.png"

export default function Login() {
  const REST_API_KEY = import.meta.env.VITE_REST_API_KEY;
  const REDIRECT_URI = import.meta.env.VITE_REDIRECT_URI_SERVER;
  const link = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}`;

  const kakaoIcon =(

      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
           className="w-6 h-6">
        <path stroke-linecap="round" stroke-linejoin="round"
              d="M12 20.25c4.97 0 9-3.694 9-8.25s-4.03-8.25-9-8.25S3 7.444 3 12c0 2.104.859 4.023 2.273 5.48.432.447.74 1.04.586 1.641a4.483 4.483 0 0 1-.923 1.785A5.969 5.969 0 0 0 6 21c1.282 0 2.47-.402 3.445-1.087.81.22 1.668.337 2.555.337Z"/>
      </svg>
  );


  const handleButtonClick = async () => {

    window.location.href = link;

  };


  return (
      <div className=" flex flex-col">
        <div className="flex flex-col my-30">
          <div className="flex justify-center">
            <img src={logo} className=""/>

          </div>
          <div className="mt-20">
            <h1 className="flex justify-center font-['FasterOne'] font-extrabold text-5xl text-center">
              WHIRRLYRIC
            </h1>
          </div>


          <div className="flex justify-center my-20">
            <button onClick={handleButtonClick}
                    className="font-['Pretendard'] font-extrabold text-xl border-solid border-4 border-yellow-400 bg-yellow-400 text-black rounded-2xl w-80">
              <div className="flex flex-row justify-center">
                <div className="mr-3">
                  {kakaoIcon}</div>
                <div> 카카오톡 로그인</div>
              </div>
            </button>
          </div>

        </div>


      </div>
  );
}
