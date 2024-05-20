interface ButtonProps {
  label: string;
  onClick: () => void;
  addClass?: string;
}

export default function Button(buttonProps: ButtonProps) {
  return (
    <button
      className={`bg-black text-white rounded-3xl w-80 h-12 ${buttonProps.addClass}`}
      onClick={buttonProps.onClick}
    >
      {buttonProps.label}
    </button>
  );
}
