import { useState } from "react";
import Button from "../components/common/Button";
import { mypageAPI } from "../api/mypageAPI";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

export default function ChangeName() {
  const navigate = useNavigate();
  const [nickname, setNickname] = useState("");

  const ChangeName = async (nickname: string) => {
    try {
      const response = await mypageAPI.changeName(nickname);
      navigate("/mypage");

      console.log(response);
    } catch (error) {
      Swal.fire({
        title: "변경 실패!",
        text: "불가능한 닉네임 입니다.",
        icon: "warning"
      });
      console.error(error);
    }
  };

  const handleOnClick = () => {
    ChangeName(nickname);
  };

  return (
    <div className="flex flex-col h-[600px] md:h-96 justify-between">
      <div className="flex flex-col">
        <label htmlFor="nickname-input" className="mx-auto mb-6">
          변경할 닉네임을 입력해 주세요.
        </label>
        <div className="mb-6 px-12">
          <input
            type="text"
            id="nickname-input"
            onChange={(e) => setNickname(e.target.value)}
            className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          />
        </div>
        <div className="flex flex-col items-center">
          <span className=" mb-2 text-sm font-medium text-gray-900 dark:text-white">
            닉네임은 최대 10자까지 가능해요.
          </span>
          <span className=" mb-2 text-sm font-medium text-gray-900 dark:text-white">
            알파벳 대소문자, 한글, 숫자만 가능해요.
          </span>
        </div>
      </div>
      <Button label="Done" addClass="mx-auto" onClick={handleOnClick} />
    </div>
  );
}
