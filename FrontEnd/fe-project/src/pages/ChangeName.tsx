import Button from "../components/common/Button";

export default function ChangeName() {
  const handleOnClick = () => {};

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
      <Button
        label="Done"
        addClass="mx-auto"
        onClick={() => {
          handleOnClick;
        }}
      />
    </div>
  );
}
